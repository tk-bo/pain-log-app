import { Component, EventEmitter, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { InputComponent } from '../../../shared/input/input.component';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-add-button',
    standalone: true,
    imports: [
      CommonModule,
      MatIconModule,
      MatButtonModule,
    ],
  templateUrl: './add-button.component.html',
  styleUrl: './add-button.component.less'
})
export class AddButtonComponent {
  @Output() add = new EventEmitter<any[]>();

  constructor(public dialog: MatDialog) { }

  openDialog(): void {
    const dialogRef = this.dialog.open(InputComponent, {
      width: "600px",
      data: {},
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.add.emit(result);
      }
    });
  }
}
