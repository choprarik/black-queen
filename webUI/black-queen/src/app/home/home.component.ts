import { Component } from "@angular/core";
import { OnInit } from "@angular/core";
import { NotificationService } from "../notifications/notification.service";

export interface PlayerRank {
  name: string;
  rank: number;
  points: number;
}

const LEADERBOARD_DATA: PlayerRank[] = [
  { rank: 1, name: 'Hydrogen', points: 1.0079 },
  {rank: 2, name: 'Helium', points: 4.0026 },
  {rank: 3, name: 'Lithium', points: 6.941 },
  {rank: 4, name: 'Beryllium', points: 9.0122 },
  {rank: 5, name: 'Boron', points: 10.811 },
  {rank: 6, name: 'Carbon', points: 12.0107 },
  {rank: 7, name: 'Nitrogen', points: 14.0067 },
  {rank: 8, name: 'Oxygen', points: 15.9994 },
  {rank: 9, name: 'Fluorine', points: 18.9984 },
  {rank: 10, name: 'Neon', points: 20.1797 },
];

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
  })
  export class HomeComponent implements OnInit {

    displayedColumns: string[] = ['rank', 'name', 'points'];
    dataSource = LEADERBOARD_DATA;
    
    constructor(public notificationService: NotificationService) {}
    
    ngOnInit(): void {
      this.notificationService.setNotificationNumbers();
    }
  }