import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PartyComponent } from './party/party.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    PartyComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'shared-expenses-fe-app';
}
