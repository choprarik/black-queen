import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConstants } from '../app-constants';
import { User } from './models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

    constructor(private httpClient: HttpClient) {}

    public loggedInUser: User;

    public getUsers() {
        return this.httpClient.get(AppConstants.SERVICE_URL.concat(AppConstants.URL.USERS_API));
    }

    public fetchUser(userId: string) {
        this.getUser(userId).subscribe((user: User) => {
            this.loggedInUser = user;
        }, (err) => {
            console.error(err);
        });
    }

    private getUser(userId: string) {
        return this.httpClient.get(AppConstants.SERVICE_URL.concat(AppConstants.URL.USER_API) + '?id=' + userId);
    }
}