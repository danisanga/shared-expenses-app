import { UUID } from "crypto";
import { Expense } from "./expense";

export interface Party {
    id: UUID,
    name: string,
    expenses: Expense[]
}
