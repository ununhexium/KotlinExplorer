const assert = require('assert');
const firebase = require('@firebase/testing')

const PROJECT_ID = "kotlin-explorer"


class Authentication {
    uid: string;
    email: string;

    constructor(uid: string, email: string) {
        this.uid = uid;
        this.email = email;
    }
}

beforeEach(async () => {
    await firebase.clearFirestoreData({projectId: PROJECT_ID});
})

describe("Security rules test", () => {

    let userAbcAuth: Authentication = new Authentication("user_abc", "user_abc@example.com");

    function getFirestore(auth: Authentication | null) {
        if (auth != null) {
            return firebase.initializeTestApp({projectId: PROJECT_ID, auth: auth}).firestore();
        } else {
            return firebase.initializeTestApp({projectId: PROJECT_ID}).firestore();
        }
    }

    function getAdminFirestore() {
        return firebase.initializeAdminApp({projectId: PROJECT_ID}).firestore();
    }

    it("Can access the user's extra lessons request", async () => {
            const db = getFirestore(null);
            const testDoc = db
                .collection("users")
                .doc(userAbcAuth.uid)
                .collection("extraLessonRequest")
                .doc("singleton");
            await firebase.assertSucceeds(testDoc.set({dummy: "data"}))
        }
    )

    it("Can read items in the read-only collection", async () => {
            const db = getFirestore(null);
            const testDoc = db.collection("readonly").doc("testDoc");
            await firebase.assertSucceeds(testDoc.get());
        }
    );

    it("Can't write items in the read-only collection", async () => {
            const db = getFirestore(null);
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
        const db = getFirestore(null);
        const testDoc = db.collection("posts").where("visibility", "==", "public");
        await firebase.assertSucceeds(testDoc.get());
    });

    it("Can query personal posts", async () => {
        const db = getFirestore(userAbcAuth);
        const testDoc = db.collection("posts").where("authorId", "==", userAbcAuth.uid);
        await firebase.assertSucceeds(testDoc.get());
    });

    it("Can't query all posts", async () => {
        const db = getFirestore(null);
        const testDoc = db.collection("posts");
        await firebase.assertFails(testDoc.get());
    });

    it("Can read a single public post", async () => {
        const admin = getAdminFirestore()
        let postId = "public_post";
        let setupDoc = admin.collection("posts").doc(postId);
        await setupDoc.set({authorId: "user_xyz", visibility: "public"});

        const db = getFirestore(null);
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
