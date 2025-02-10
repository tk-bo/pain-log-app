import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { PainLogResultDataEntity } from '../../../shared/models/pain-log-data-entity';
import { ApiService } from '../../../infrastructures/services/api.service';
import { SearchComponent } from '../../components/search/search.component';
import { Observable, of } from 'rxjs';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatNativeDateModule } from '@angular/material/core';
import { ReactiveFormsModule } from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import { DataTableComponent } from '../../components/data-table/data-table.component';
import { AddButtonComponent } from '../../components/add-button/add-button.component';


@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    SearchComponent,
    MatDialogModule,
    MatIconModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatButtonModule,
    DataTableComponent,
    AddButtonComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.less'],
  providers: []
})

export class DashboardComponent implements OnInit {
  displayedColumns: string[] = ['date', 'name', 'movement', 'vas', 'memo', 'actions'];
  dataSource$!: Observable<PainLogResultDataEntity[]>;

  constructor(private service: ApiService, public dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadPainlog();
  }

  loadPainlog(): void {
    this.dataSource$ = this.service.selectPatients();
  }

  onSearchCompleted(data: PainLogResultDataEntity[]): void {
    this.dataSource$ = of(data);
  }

}
