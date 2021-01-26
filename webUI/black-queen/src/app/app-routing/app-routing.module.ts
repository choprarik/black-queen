import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavComponent } from '../core/nav/nav.component';
import { GameRoomComponent } from '../game/game-room/game-room.component';
import { HomeComponent } from '../home/home.component';
import { LoginComponent } from '../login/login/login.component';
import { SignupComponent } from '../login/signup/signup.component';
import { RoomComponent } from '../room/room.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'default', component: NavComponent, children: [
    { path: 'home', component: HomeComponent },
    { path: 'room', component: RoomComponent },
    { path: 'game', component: GameRoomComponent }
  ] },
  { path: '**', component: LoginComponent}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
