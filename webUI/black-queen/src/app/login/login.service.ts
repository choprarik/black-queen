import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConstants } from '../app-constants';
import { LoginRequest } from './models/login-request.model';
import { SignupRequestModel } from './models/signup-request.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  login(loginRequest: LoginRequest) {
    return this.httpClient.post(AppConstants.SERVICE_URL + AppConstants.URL.LOGIN_API, loginRequest);
  }

  signup(signupRequest: SignupRequestModel) {
    return this.httpClient.post(AppConstants.SERVICE_URL + AppConstants.URL.SIGNUP_API, signupRequest);
  }
}
