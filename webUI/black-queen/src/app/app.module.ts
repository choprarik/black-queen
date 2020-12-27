import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing/app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { GameModule } from './game/game.module';
import { HomeComponent } from './home/home.component';
import { LoginModule } from './login/login.module';
import { MaterialModule } from './material/material.module';
import { JoinRoomComponent } from './join-room/join-room.component';
import { RoomModule } from './room/room.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    JoinRoomComponent
  ],
  imports: [
    BrowserModule,
    NoopAnimationsModule,
    FormsModule,
    MaterialModule,
    LoginModule,
    GameModule,
    AppRoutingModule,
    CoreModule,
    RoomModule
  ],
  exports: [
    FormsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
