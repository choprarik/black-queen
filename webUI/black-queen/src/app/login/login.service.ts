import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConstants } from '../app-constants';
import { LoginRequest } from './models/login-request.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  login(loginRequest: LoginRequest) {
    return this.httpClient.post(AppConstants.LOGIN_SERVICE_URL + AppConstants.URL.LOGIN_API, loginRequest);
  }
}
