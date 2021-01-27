import { Component, OnInit } from "@angular/core";
import { MatDialog } from "@angular/material/dialog";
import { Router } from "@angular/router";
import { UsersModel } from "../core/models/users.model";
import { RoomService } from "../core/room.service";
import { UserService } from "../core/user.service";
import { JoinRoomComponent } from "../join-room/join-room.component";
import { RoomModel } from "../room/models/room.model";

export interface PlayerRank {
  name: string;
  rank: number;
  points: number;
}

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
  })
  export class HomeComponent implements OnInit {

    displayedColumns: string[] = ['rank', 'name', 'points'];
    dataSource: PlayerRank[] = [];
    
    constructor(private userService: UserService,
      public dialog: MatDialog,
      private roomService: RoomService,
      private router: Router) {}
    
    ngOnInit(): void {
      // get rank wise data for leader board
      this.getLeaderBoardData();
    }

    public getLeaderBoardData() {
      this.userService.getUsers().subscribe((data: UsersModel) => {
        if (data && data.users && data.users.length > 0) {
          data.users.sort((a,b) => {
            if (a.points > b.points) {
              return 1;
            } else {
              return -1;
            }
          });
          for (let i=0; i < data.users.length; i++) {
            const x: PlayerRank = {
              rank: i+1,
              name: data.users[i].name,
              points: data.users[i].points
            };
            this.dataSource.push(x);
          }
          this.dataSource = [...this.dataSource];
        } 
      }, (err) => {
        console.error(err);
      });
    }

    /**
     * call create room method from RoomService
     */
    public createRoom() {
      this.roomService.createRoom().subscribe((data: RoomModel) => {
        // set room
        this.router.navigate(['/default/room']);
      }, (err) => {
        console.error(err);
      })
      this.router.navigate(['/default/room']);
    }

    openJoinRoomDialog () {
      const dialogRef = this.dialog.open(JoinRoomComponent, {
        width: '250px'
      });
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        // join given room
      });
    }
  }