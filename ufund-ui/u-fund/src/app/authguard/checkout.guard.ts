import { CanActivateChildFn, Router } from '@angular/router';
import { inject } from '@angular/core';


export const checkoutGuard: CanActivateChildFn = (childRoute, state) => {
  const router = inject(Router);

  const localData = localStorage.getItem('token');
  
  if(localData == 'user-token')
  {
    return true;
  }
  else if (localData == undefined)
  {
    alert("You do not have permission to access this page.")
    router.navigate(['/login']);
    return false;
  }
  else 
  {
    alert("You do not have permission to access this page.")
    router.navigate(['/needs']);
    return false;
  }
};
