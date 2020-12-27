import { Injectable } from '@angular/core';
import { NotificationModel } from './models/notification.model';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor() { }

  public notifications: any[] = [{
    title: 'notification title 1',
    text: 'abcd'
  },
  {
    title: 'notification title 2',
    text: 'abcd2'
  },
  {
    title: 'notification title 3',
    text: 'abcd3'
  },
  {
    title: 'notification title 4',
    text: 'abcd4'
  }];
  public notificationNumbers: number = 2;

  public addNotification(notifications: NotificationModel[]): void {
    this.notifications.concat(notifications);
    this.notificationNumbers = this.notifications.length;
  }

  public getNotifications(): NotificationModel[] {
    return this.notifications;
  }

  public setNotificationNumbers() {
    this.notificationNumbers = this.notifications.length;
  }
}
