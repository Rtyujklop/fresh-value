import { Injectable } from '@angular/core';
import { Need } from './need'
import { NEEDS } from './mock-needs';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NeedService {

  constructor() 
  { 
    
  }

  getNeeds(): Observable<Need[]>  
  {
    const needs = of(NEEDS)
    return needs;
  }
}
