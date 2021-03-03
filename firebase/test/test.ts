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

    let userA: Authentication = new Authentication("user_abc", "user_abc@example.com");
    let userZ: Authentication = new Authentication("user_xyz", "user_xyz@example.com");

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

    it("Can write the user's extra lessons request", async () => {
            let user = userA;

            const db = getFirestore(user);
            const testDoc = db
                .collection("users")
                .doc(user.uid)
                .collection("extraLessonRequest")
                .doc("singleton");
            await firebase.assertSucceeds(testDoc.set({dummy: "data"}))
        }
    )

    it("Can read the user's extra lessons request", async () => {
            let user = userA;

            const db = getFirestore(user);
            const testDoc = db
                .collection("users")
                .doc(user.uid)
                .collection("extraLessonRequest")
                .doc("singleton");
            await firebase.assertSucceeds(testDoc.get())
        }
    )

    it("Prevent other users from writing extra lessons requests", async () => {
            const db = getFirestore(userA);
            const testDoc = db
                .collection("users")
                .doc(userZ.uid)
                .collection("extraLessonRequest")
                .doc("singleton");

            await firebase.assertFails(testDoc.set({dummy: "data"}))
        }
    )

    it("Prevent other users from reading extra lessons requests", async () => {
            const db = getFirestore(userA);
            const testDoc = db
                .collection("users")
                .doc(userZ.uid)
                .collection("extraLessonRequest")
                .doc("singleton");

            await firebase.assertFails(testDoc.get())
        }
    )
});
