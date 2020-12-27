import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { RoomService } from '../core/room.service';

@Component({
  selector: 'app-join-room',
  templateUrl: './join-room.component.html',
  styleUrls: ['./join-room.component.css']
})
export class JoinRoomComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<JoinRoomComponent>,
    private roomService: RoomService,
    private router: Router) { }

  public roomId: string;

  ngOnInit(): void {
  }

  /**
   * Close dialog on cancel press
   */
  cancel() {
    // close dialog
    this.dialogRef.close();
  }

  /**
   * This method will calls join Room method for ROOM service
   */
  joinRoom() {
    if (this.roomId) {
      // join a room
      this.roomService.joinRoom(this.roomId).subscribe(() => {
        this.dialogRef.close();
        this.router.navigate(['/default/room']);
      }, (err) => {
        console.error(err);
      });
    }
  }

}
