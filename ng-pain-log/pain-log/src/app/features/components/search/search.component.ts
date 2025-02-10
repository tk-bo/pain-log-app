import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ApiService } from '../../../infrastructures/services/api.service'; 
import { PainLogResultDataEntity } from '../../../shared/models/pain-log-data-entity';
import { MatButton } from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [MatButton, MatCardModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.less'
})
export class SearchComponent implements OnInit {
  @Output() searchCompleted = new EventEmitter<any[]>();
  painLog: PainLogResultDataEntity[] = [];

  constructor(private apiService: ApiService, ) {}

  ngOnInit(): void { }

  onSearch(name: string, movement: string): void {
    this.apiService.searchPatients(name, movement).subscribe((data) => {
      this.painLog = data;
      this.searchCompleted.emit(this.painLog);
    })
    
  }

  onClear(name: HTMLInputElement, movement: HTMLInputElement): void {
    name.value = '';
    movement.value = '';
  }
}
