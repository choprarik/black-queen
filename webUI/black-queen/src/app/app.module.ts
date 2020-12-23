import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { AppComponent } from './app.component';
import { GameModule } from './game/game.module';
import { HomeComponent } from './home/home.component';
import { LoginModule } from './login/login.module';
import { MaterialModule } from './material/material.module';
import { NotificationsComponent } from './notifications/notifications.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NotificationsComponent
  ],
  imports: [
    BrowserModule,
    NoopAnimationsModule,
    FormsModule,
    MaterialModule,
    LoginModule,
    GameModule,
    AppRoutingModule
  ],
  exports: [
    FormsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
