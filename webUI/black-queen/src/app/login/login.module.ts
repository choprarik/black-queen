import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { LoginService } from './login.service';
import { MaterialModule } from '../material/material.module';



@NgModule({
  declarations: [LoginComponent],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    MaterialModule
  ],
  exports: [
    LoginComponent
  ],
  providers: [LoginService]
})
export class LoginModule { }
