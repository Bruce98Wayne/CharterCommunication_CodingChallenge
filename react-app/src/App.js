import logo from "./logo.svg";
import "./App.css";
import RewardPointCalculator from "./rewardPointCalculator";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        {/* <img src={logo} className="App-logo" alt="logo" /> */}
        <p>
          Click on the button below to compute the Total and Monthly rewards:
        </p>
        <RewardPointCalculator />
      </header>
    </div>
  );
}

export default App;
