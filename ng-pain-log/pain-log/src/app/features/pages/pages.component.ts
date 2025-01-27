import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { HeaderComponent } from '../../core/header/header.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-pages',
  standalone: true,
  imports: [CommonModule, RouterModule, HeaderComponent],
  templateUrl: './pages.component.html',
  styleUrl: './pages.component.less'
})
export class PagesComponent {

}
