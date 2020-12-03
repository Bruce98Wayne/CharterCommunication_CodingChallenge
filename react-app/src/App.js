import logo from "./logo.svg";
import "./App.css";
import RewardPointCalculator from "./rewardPointCalculator";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        {/* <img src={logo} className="App-logo" alt="logo" /> */}
        <div>
          <h4>Transactions: </h4>
          <ul>
            <li>
              <b>January: </b>[120]
            </li>
            <li>
              <b> February: </b>[70, 100, 150]
            </li>
            <li>
              <b>March: </b>[10, 20, 180]
            </li>
          </ul>
        </div>
        <p>
          Click on the button below to compute the Total and Monthly rewards:
        </p>
        <RewardPointCalculator />
        <h6>
          Check the console (Ctrl + shift + i) to view the monthly rewards!
        </h6>
      </header>
    </div>
  );
}

export default App;
