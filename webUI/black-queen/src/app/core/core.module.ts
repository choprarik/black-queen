import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from '../app-routing/app-routing.module';
import { MaterialModule } from '../material/material.module';
import { NavComponent } from './nav/nav.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { PopoverComponent } from './popover/popover.component';
import { RoomService } from './room.service';
import { UserService } from './user.service';

@NgModule({
  declarations: [PopoverComponent, NavComponent, NotificationsComponent],
  imports: [
    CommonModule,
    MaterialModule,
    AppRoutingModule
  ],
  providers: [UserService, RoomService]
})
export class CoreModule { }
