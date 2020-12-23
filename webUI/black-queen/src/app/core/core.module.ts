import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PopoverComponent } from './popover/popover.component';
import { MaterialModule } from '../material/material.module';



@NgModule({
  declarations: [PopoverComponent],
  imports: [
    CommonModule,
    MaterialModule
  ]
})
export class CoreModule { }
