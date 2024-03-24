import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PartyComponent } from './party/party.component';
import { BalanceComponent } from './balance/balance.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    PartyComponent,
    BalanceComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'shared-expenses-fe-app';
}
