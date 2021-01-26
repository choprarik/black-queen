import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoomComponent } from './room.component';
import { CoreModule } from '../core/core.module';
import { MaterialModule } from '../material/material.module';



@NgModule({
  declarations: [RoomComponent],
  imports: [
    CommonModule,
    CoreModule,
    MaterialModule
  ]
})
export class RoomModule { }
