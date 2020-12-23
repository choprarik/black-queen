import { Component, OnInit } from '@angular/core';
import { GameRoomService } from './game-room.service';
import { Card } from '../card/card';
import { Player } from '../player';

@Component({
  selector: 'app-game-room',
  templateUrl: './game-room.component.html',
  styleUrls: ['./game-room.component.css'],
  providers: [GameRoomService]
})
export class GameRoomComponent implements OnInit {

  public listPlayerCards: Array<Card>;
  public players: Array<Player> = new Array<Player>();

  constructor(private _gameRoomService: GameRoomService) { }

  ngOnInit() {
    this.listPlayerCards = this._gameRoomService.getPlayerCards();
    for (let i=0; i<6; i++) {
      this.players.push(new Player);
    }
  }

  clickedCard(card: Card) {
    this._gameRoomService.clickedCard(card);
    this.players[0].card = card;
    this.listPlayerCards.splice(this.listPlayerCards.indexOf(card), 1);
  }

}
