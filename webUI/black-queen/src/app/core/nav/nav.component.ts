import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../notifications/notification.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  constructor(public notificationService: NotificationService) { }

  ngOnInit(): void {
    this.notificationService.setNotificationNumbers();
  }

}
