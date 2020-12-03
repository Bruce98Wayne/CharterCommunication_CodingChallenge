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
        (rewardAccumilator, transactionValue) =>
          rewardAccumilator + transactionValue
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
        <br></br>
        <div>{this.state.totalReward}</div>
      </div>
    );
  }
}
