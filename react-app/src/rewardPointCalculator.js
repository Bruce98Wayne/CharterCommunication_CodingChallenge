import React from "react";
import { MonthlyTransactions } from "./data/transactionData";

export default class RewardPointCalculator extends React.Component {
  state = {
    varMonthlyReward: [],
    totalReward: 0,
  };

  constructor(props) {
    super(props);
    this.state = {
      monthlyReward: {},
      totalReward: 0,
    };
  }

  trasnactions = {};

  computeRewards = () => {
    MonthlyTransactions.forEach((monthlyTransaction) => {
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

      this.setState(
        (state) => {
          return {
            totalReward: monthlyTransacationValue + state.totalReward,
            monthlyReward,
          };
        },
        () => console.log(this.state)
      );
    });
  };

  render() {
    return (
      <div>
        <button className="compute" onClick={() => this.computeRewards()}>
          Compute Rewards
        </button>
        <br />
        <br />
        <div>
          <header>Total Rewards: </header>
          {this.state.totalReward}
        </div>
      </div>
    );
  }
}
