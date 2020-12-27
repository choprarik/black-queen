import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { IdModel } from 'src/app/core/models/id.model';
import { UserService } from 'src/app/core/user.service';
import { LoginService } from '../login.service';
import { LoginRequest } from '../models/login-request.model';
import { LoginResponse } from '../models/login-response';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService,
    private _snakcBar: MatSnackBar,
    private _router: Router,
    private userService: UserService) { }

  public loginInfo: LoginRequest = {
    username: '',
    passcode: ''
  };

  public onLoginClick() {
    this.loginService.login(this.loginInfo).subscribe((userId: IdModel) => {
      if (userId) {
        this._snakcBar.open('Successfully logged in.', 'Close', {
          duration: 3000,
          verticalPosition: 'top'
        });
        this.userService.fetchUser(userId.id);
        this._router.navigate(['/home']);
      }
    }, (err) => {
      /* if (err.error) {
        const er: LoginErrorModel = err.error;
        const message = er.errorCode + ': ' + er.message;
        this._snakcBar.open(message, 'Close', {
          duration: 3000,
          verticalPosition: 'top'
        });
      } */

    });
  }

  ngOnInit(): void {
  }

}
