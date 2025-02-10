import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable, of } from 'rxjs';
import { InputComponent } from '../../../shared/input/input.component';
import { PainLogResultDataEntity } from '../../../shared/models/pain-log-data-entity';
import { ApiService } from '../../../infrastructures/services/api.service';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-data-table',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatIconModule,
    MatButtonModule,
  ],
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.scss']
})
export class DataTableComponent {
  @Input() dataSource$!: Observable<PainLogResultDataEntity[]>;

  constructor(private service: ApiService, public dialog: MatDialog) { }

  displayedColumns: string[] = ['date', 'name', 'movement', 'vas', 'memo', 'actions'];

  loadPainlog(): void {
    this.service.selectPatients().subscribe(data => {
      this.dataSource$ = of(data);
    });
  }

  onDelete(element: PainLogResultDataEntity): void {
    this.service.deletePatients(element.id).subscribe(() => {
      this.loadPainlog();
    });
  }

  onUpdate(element: PainLogResultDataEntity): void {
    const dialogRef = this.dialog.open(InputComponent, {
      width: '600px',
      data: element,
      disableClose: true
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.loadPainlog();
      }
    })
  }

}
