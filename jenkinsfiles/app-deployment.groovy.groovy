@Library('common-libraries') _
deployApp(
  product: 'prd',
  gitRepo: 'lds/terraform-aws-coreinfra/aws-main-prd', // path to repo
  gitTagOrBranch: 'branch',
  tfParallelism: '1',
  codePath: '04_eks', // path what to apply without leading slash
  deployAdditionalArgs: '-target=module.luminor_eks_addons',
  iamRole: 'arn:aws:iam::616575394798:role/a-prd-rol-jenkins-coreinfra-deployer-eks',
)
