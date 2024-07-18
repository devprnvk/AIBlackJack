import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score
import joblib

data = pd.read_csv('blackjack_hands.csv')

print(data.head())

features = ['card1', 'card2', 'card3', 'card4', 'card5', 'dealcard1']
target = 'winloss'

data[target] = data[target].map({'Win': 1, 'Loss': 0})

X = data[features]
y = data[target]

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

model = RandomForestClassifier()

model.fit(X_train, y_train)

predictions = model.predict(X_test)
print('Accuracy:', accuracy_score(y_test, predictions))

joblib.dump(model, 'blackjack_ai_model.pkl')
import joblib
import sys
import pandas as pd

model = joblib.load('blackjack_ai_model.pkl')

card1 = int(sys.argv[1])
card2 = int(sys.argv[2])
card3 = int(sys.argv[3])
card4 = int(sys.argv[4])
card5 = int(sys.argv[5])
dealcard1 = int(sys.argv[6])

input_data = pd.DataFrame([[card1, card2, card3, card4, card5, dealcard1]],
                          columns=['card1', 'card2', 'card3', 'card4', 'card5', 'dealcard1'])

action = model.predict(input_data)[0]

print(action)