import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { TotalBalance } from "../interfaces/totalBalance";

@Injectable({
    providedIn: 'root'
})
export class BalancesService {
    constructor(private http: HttpClient) { }

    getData(): Observable<TotalBalance> {
        return this.http.get<TotalBalance>('balances/dc4c885a-0c0c-4575-90a3-890bf78a1090');
    }
}