import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GameRoomComponent } from './game-room/game-room.component';
import { CardComponent } from './card/card.component';
import { MaterialModule } from '../material/material.module';
import { GameRoomService } from './game-room/game-room.service';



@NgModule({
  declarations: [GameRoomComponent, CardComponent],
  imports: [
    CommonModule,
    MaterialModule
  ],
  providers: [
    GameRoomService
  ],
  exports: [
    GameRoomComponent,
    CardComponent
  ]
})
export class GameModule { }
