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