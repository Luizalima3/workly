import Elysia from "elysia";
import { Auth } from "./auth.service";

const AuthController = new Elysia().group("/auth", (app) => {

    app.post("signup", async () => {
        const response = Auth.signUp({password: "xx", username: "xx"});

        //TODO
    })

    return app;
})