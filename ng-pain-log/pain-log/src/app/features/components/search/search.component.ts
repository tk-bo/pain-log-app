import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ApiService } from '../../../infrastructures/services/api.service'; 

@Component({
  selector: 'app-search',
  imports: [],
  templateUrl: './search.component.html',
  styleUrl: './search.component.less'
})
export class SearchComponent implements OnInit {
  @Output() searchComplted = new EventEmitter<any[]>();
  painLog: any[] = [];

  constructor(private apiService: ApiService) {}

  ngOnInit(): void { }

  // onSerarch(name: String, movement: String): void {
  //   this.apiService.select(name, movement).subcribe((data) => {
  //     this.painLog = data;
  //     this.searchComplted.emit(this.painLog);
  //   })
    
  // }
}
