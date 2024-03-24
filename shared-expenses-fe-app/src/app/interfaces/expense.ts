import { UUID } from "crypto";
import { Friend } from "./friend";

export interface Expense {
    id: UUID,
    description: string,
    quantity: number,
    friend: Friend
}