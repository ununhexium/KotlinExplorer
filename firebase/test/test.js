const assert = require('assert');
const firebase = require('@firebase/testing')

const PROJECT_ID = "kotlin-explorer"



beforeEach(async () => {
    await firebase.clearFirestoreData({projectId: PROJECT_ID});
})

describe("Security rules test", () => {
    it("Maths", () =>
        assert.strictEqual(2 + 2, 4)
    );

    function getFirestore(auth) {
        return firebase.initializeTestApp({projectId: PROJECT_ID, auth: auth}).firestore();
    }

    function getAdminFirestore() {
        return firebase.initializeAdminApp({projectId: PROJECT_ID}).firestore();
    }

    let userAbcAuth = {uid: "user_abc", email: "user_abc@example.com"};

    it("Can read items in the read-only collection", async () => {
            const db = getFirestore();
            const testDoc = db.collection("readonly").doc("testDoc");
            await firebase.assertSucceeds(testDoc.get());
        }
    );

    it("Can't write items in the read-only collection", async () => {
            const db = getFirestore();
            const testDoc = db.collection("readonly").doc("testDoc2");
            await firebase.assertFails(testDoc.set({foo: "bar"}));
        }
    );

    it("Can write to a user document with the same id as our user", async () => {
            const db = getFirestore(userAbcAuth);
            const testDoc = db.collection("users").doc("user_abc");
            await firebase.assertSucceeds(testDoc.set({foo: "bar"}));
        }
    );

    it("Can't write to a user document with a different ID as our user", async () => {
            const db = getFirestore(userAbcAuth);
            const testDoc = db.collection("users").doc("user_xyz");
            await firebase.assertFails(testDoc.set({foo: "bar"}));
        }
    );

    it("Can read posts that are marked public", async () => {
        const db = getFirestore();
        const testDoc = db.collection("posts").where("visibility", "==", "public");
        await firebase.assertSucceeds(testDoc.get());
    });

    it("Can query personal posts", async () => {
        const db = getFirestore(userAbcAuth);
        const testDoc = db.collection("posts").where("authorId", "==", userAbcAuth.uid);
        await firebase.assertSucceeds(testDoc.get());
    });

    it("Can't query all posts", async () => {
        const db = getFirestore();
        const testDoc = db.collection("posts");
        await firebase.assertFails(testDoc.get());
    });

    it("Can read a single public post", async () => {
        const admin = getAdminFirestore()
        let postId = "public_post";
        let setupDoc = admin.collection("posts").doc(postId);
        await setupDoc.set({authorId: "user_xyz", visibility: "public"});

        const db = getFirestore();
        const testDoc = db.collection("posts").doc(postId);
        await firebase.assertSucceeds(testDoc.get());
    });

    it("Can read a single private post belonging to the user", async () => {
        const admin = getAdminFirestore()
        let postId = "private_post";
        let setupDoc = admin.collection("posts").doc(postId);
        await setupDoc.set({authorId: userAbcAuth.uid, visibility: "private"});

        const db = getFirestore(userAbcAuth);
        const testDoc = db.collection("posts").doc(postId);
        await firebase.assertSucceeds(testDoc.get());
    });
});