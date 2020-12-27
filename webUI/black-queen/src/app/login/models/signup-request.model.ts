import { User } from "src/app/core/models/user.model";
import { LoginRequest } from "./login-request.model";

export class SignupRequestModel {
    userInfo: User;
    loginInfo: LoginRequest;
}