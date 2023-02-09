import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import './App.css';
import { Home } from './Pages/Home/index';
import { Footer } from './Components/Footer/index';
import { DetailsPage } from './Pages/DetailsPage/index';
import { Modification } from './Pages/Modification';

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/modification">
          <Modification />
        </Route>  
        <Route path="/:place">
          {console.log("Here we are: DetailsPage")}
          <DetailsPage />
        </Route>
        <Route path="/">
          {console.log("Here we are: Home")}
          <Home />
        </Route>
      </Switch>
      <Footer />
    </Router>
  );
}

export default App;
