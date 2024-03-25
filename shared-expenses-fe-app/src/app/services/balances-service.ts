import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { TotalBalance } from "../interfaces/totalBalance";
import { UUID } from "node:crypto";

@Injectable({
    providedIn: 'root'
})
export class BalancesService {
    constructor(private http: HttpClient) { }

    getData(id: UUID): Observable<TotalBalance> {
        return this.http.get<TotalBalance>('balances/' + id);
    }
}