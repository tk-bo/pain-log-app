import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PainLogResultDataEntity } from '../../shared/models/pain-log-data-entity';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private url = "http://localhost:8081"

  constructor( private http: HttpClient ) { }

  insertPatients(patient: PainLogResultDataEntity): Observable<PainLogResultDataEntity> {
    return this.http.post<PainLogResultDataEntity>(`${this.url}/insert`, patient);
  }

  selectPatients(): Observable<PainLogResultDataEntity[]> {
    return this.http.get<PainLogResultDataEntity[]>(`${this.url}/select`);
  }

  searchPatients(user: string, movement: string): Observable<PainLogResultDataEntity[]> {
    return this.http.get<PainLogResultDataEntity[]>(`${this.url}/search?user=${user}&movement=${movement}`);
  }

  updatePatients(patient: PainLogResultDataEntity): Observable<PainLogResultDataEntity> {
    return this.http.put<PainLogResultDataEntity>(`${this.url}/update`, patient);
  }

  deletePatients(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/delete/${id}`);
  }

}
