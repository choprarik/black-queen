import { Component, OnInit, ViewChild } from '@angular/core';
import { MatMenu } from '@angular/material/menu';

@Component({
  selector: 'app-popover',
  templateUrl: './popover.component.html',
  styleUrls: ['./popover.component.css']
})
export class PopoverComponent implements OnInit {

  @ViewChild('popover') popover: MatMenu;
  constructor() { }

  ngOnInit(): void {
  }

  public openPopover() {
    // open popover
  }

}
