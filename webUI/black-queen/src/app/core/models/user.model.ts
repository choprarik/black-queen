import { Friend } from "./friend.model";

export class User {
    id?: string;
    name?: string;
    email?: string;
    age?: number;
    friends?: Friend[];
    points?: number;
}