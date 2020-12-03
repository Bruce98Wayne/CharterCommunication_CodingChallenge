import React from "react";
import { MonthlyTransactions } from "./data/transactionData";

export default class RewardPointCalculator extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      monthlyReward: {},
      totalReward: 0,
      disbaleButton: false,
    };
  }

  // Given a set of trnsaction data, this function computes monthly and total rewards:
  computeRewards = () => {
    // Disabling the button
    this.setState({ disbaleButton: true });

    // Iterating over the monthly transaction Data
    MonthlyTransactions.forEach((monthlyTransaction) => {
      // Computing the rewards for each month
      var monthlyTransacationValue = monthlyTransaction.transactions.reduce(
        (rewardAccumilator, transactionValue, index) => {
          if (transactionValue > 50 && transactionValue < 100) {
            rewardAccumilator += transactionValue - 50;
          } else if (transactionValue >= 100) {
            rewardAccumilator += 2 * (transactionValue - 100) + 50;
          }
          return rewardAccumilator;
        },
        0
      );

      var monthlyReward = this.state.monthlyReward;
      monthlyReward[monthlyTransaction.month] = monthlyTransacationValue;

      // Updating the state with new Rewards!
      this.setState(
        (state) => {
          return {
            totalReward: monthlyTransacationValue + state.totalReward,
            monthlyReward,
          };
        },
        () => console.log(this.state.monthlyReward)
      );
    });
  };

  render() {
    return (
      <div>
        <button
          className="compute"
          onClick={() => this.computeRewards(this)}
          disabled={this.state.disbaleButton}
        >
          Compute Rewards
        </button>
        <br />
        <br />
        <div>
          <header>Total Rewards:</header>
          {this.state.totalReward}
        </div>
      </div>
    );
  }
}
