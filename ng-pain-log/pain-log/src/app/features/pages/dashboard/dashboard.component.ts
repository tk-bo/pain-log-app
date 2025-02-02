import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import { PainLogResultDataEntity } from '../../../shared/models/pain-log-data-entity';
import { ApiService } from '../../../infrastructures/services/api.service';
import { HttpClientModule } from '@angular/common/http';

const painLog: PainLogResultDataEntity[] = [];

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, MatTableModule, HttpClientModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.less'
})

export class DashboardComponent {
  displayedColumns: string[] = ['date', 'name', 'movement', 'vas', 'memo'];
  dataSource: PainLogResultDataEntity[] = [];

  constructor(private service: ApiService) {
    this.service.selectPatients().subscribe(data => {
      this.dataSource = data;
    });
  }
}
