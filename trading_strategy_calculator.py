import pandas as pd
import numpy as np

class TradingStrategyCalculator:
    def __init__(self, data):
        self.data = data

    def calculate_signals(self):
        self.data['Signal'] = np.where(self.data['Close'].shift(1) < self.data['Close'], 'BUY', 'SELL')

    def calculate_stop_loss(self, stop_loss_percentage):
        self.data['Stop_Loss'] = self.data['Close'] * (1 - stop_loss_percentage / 100)

    def calculate_take_profit(self, take_profit_percentages):
        for i, tp in enumerate(take_profit_percentages, start=1):
            self.data[f'TP{i}'] = self.data['Close'] * (1 + tp / 100)

    def run(self, stop_loss_percentage, take_profit_percentages):
        self.calculate_signals()
        self.calculate_stop_loss(stop_loss_percentage)
        self.calculate_take_profit(take_profit_percentages)
    
# Example usage:
# data = pd.DataFrame({'Close': [...]})
# strategy = TradingStrategyCalculator(data)
# strategy.run(stop_loss_percentage=2, take_profit_percentages=[1, 2, 3])