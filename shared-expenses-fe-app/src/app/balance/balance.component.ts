import { Component, OnInit } from '@angular/core';
import { TotalBalance } from '../interfaces/totalBalance';
import { BalancesService } from '../services/balances-service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-balance',
  standalone: true,
  imports: [
    NgFor
  ],
  templateUrl: './balance.component.html',
  styleUrl: './balance.component.css'
})
export class BalanceComponent implements OnInit {

  totalBalance!: TotalBalance;

  constructor(private balancesService: BalancesService) { }

  ngOnInit() {
    this.balancesService.getData().subscribe((data: TotalBalance) => {
      this.totalBalance = data;
    });
  }

}
