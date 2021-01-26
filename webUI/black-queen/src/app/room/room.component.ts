import { Component, OnInit } from '@angular/core';
import { RoomModel } from './models/room.model';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  constructor() { }

  currentRoom: RoomModel = {};
  ngOnInit(): void {
  }

  invitePlayers() {
    // TODO
  }

  startGame() {
    // TODO
  }

}
