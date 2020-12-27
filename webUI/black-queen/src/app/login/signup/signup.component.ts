import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { IdModel } from 'src/app/core/models/id.model';
import { User } from 'src/app/core/models/user.model';
import { UserService } from 'src/app/core/user.service';
import { LoginService } from '../login.service';
import { LoginRequest } from '../models/login-request.model';
import { SignupRequestModel } from '../models/signup-request.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private loginService: LoginService,
    private _snakcBar: MatSnackBar,
    private _router: Router,
    private userService: UserService) { }

  loginInfo: LoginRequest = {
    username: '',
    passcode: ''
  };
  userInfo: User = {};

  ngOnInit(): void {
  }

  signup() {
    const signUpInfo: SignupRequestModel = new SignupRequestModel();
    signUpInfo.loginInfo = this.loginInfo;
    signUpInfo.userInfo = this.userInfo;
    this.loginService.signup(signUpInfo).subscribe((userId: IdModel) => {
      if (userId) {
        this._snakcBar.open('Successfully sign up.', 'Close', {
          duration: 3000,
          verticalPosition: 'top'
        });
        this.userService.fetchUser(userId.id);
        this._router.navigate(['/home']);
      }
    }, (err) => {
      console.error(err);
    })
  }

}
