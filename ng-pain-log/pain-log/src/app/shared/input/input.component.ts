import { Component, EventEmitter, Inject, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule, MatCalendarCellClassFunction } from '@angular/material/datepicker'
import { MatSliderModule } from '@angular/material/slider';
import { ApiService } from '../../infrastructures/services/api.service';
import { PainLogResultDataEntity } from '../models/pain-log-data-entity';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-input',
  standalone: true,
  imports: [
    CommonModule,
    MatFormFieldModule, 
    MatInputModule, 
    MatDatepickerModule, 
    MatSliderModule, 
    ReactiveFormsModule,
    MatDialogModule,
    MatButtonModule,
    FormsModule,
  ],
  templateUrl: './input.component.html',
  styleUrl: './input.component.less'
})
export class InputComponent implements OnInit {
  fg!: FormGroup;

  constructor(
    private fb: FormBuilder, 
    private service: ApiService,
    private dialogRef: MatDialogRef<InputComponent>,
    @Inject(MAT_DIALOG_DATA) public data:any
  ) {}

  ngOnInit(): void {
    this.fg = this.fb.group({
      id: [this.data?.id || 0],
      date: [this.data?.date || ''],
      name: [this.data?.name || ''],
      movement: [this.data?.movement || ''],
      vas: [this.data?.vas || 0],
      memo: [this.data?.memo || '']
    });
  }

  dateClass: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
    if (view === 'month') {
      const date = cellDate.getDate();
      return date === 1 || date === 20 ? 'example-custom-date-class' : '';
    }
    return '';
  };

  onSubmit(): void {
    if (this.fg.valid) {
      const newPainLog: PainLogResultDataEntity = {
        id: this.data?.id || 0,
        date: this.fg.value.date,
        name: this.fg.value.name,
        movement: this.fg.value.movement,
        vas: this.fg.value.vas,
        memo: this.fg.value.memo
      };

      if (newPainLog.id === 0) {
        this.service.insertPatients(newPainLog).subscribe((data: PainLogResultDataEntity[]) => {
          this.dialogRef.close(data);
        });
      } else {
        this.service.updatePatients(newPainLog).subscribe((data: PainLogResultDataEntity[]) => {
          this.dialogRef.close(data);
        });
      }
    }
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
