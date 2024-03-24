import { Component, OnInit } from '@angular/core';
import { PartiesService } from '../services/parties-service';
import { Party } from '../interfaces/party';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-party',
  standalone: true,
  imports: [
    NgFor,
  ],
  templateUrl: './party.component.html',
  styleUrl: './party.component.css'
})
export class PartyComponent implements OnInit {

  party!: Party;

  constructor(private partiesService: PartiesService) { }

  ngOnInit() {
    this.partiesService.getData().subscribe((data: Party) => {
      this.party = data;
    });
  }

}
