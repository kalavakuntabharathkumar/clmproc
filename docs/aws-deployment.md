# AWS deployment notes

Suggested learning deployment:
1. Push a container image to Amazon ECR.
2. Run it on EC2 or ECS.
3. Use Amazon RDS for PostgreSQL.
4. Store secrets in AWS Secrets Manager or SSM Parameter Store.
5. Add TLS, least-privilege IAM, backups, CloudWatch alarms, and network restrictions.

This scaffold is not HIPAA-compliant or production-ready. Never use real patient/claim data. Add authorization, audit logging, encryption, retention controls, and formal security/compliance review before real use.
